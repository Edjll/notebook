import './form-select.css';
import React, {FormEvent, useEffect, useState} from "react";
import eraseIcon from '../../../image/erase.png';

export interface FormSelectItem {
    key: any,
    value: string
}

export interface FormSelectProps {
    title: string,
    selectedItem: FormSelectItem | null,
    inputHandler: Function,
    items: FormSelectItem [],
    className?: string
}

const FormSelect = ({title, selectedItem, inputHandler, className, items}: FormSelectProps) => {
    const classList = ['form-select'];
    const [active, setActive] = useState(false);
    const ref = React.createRef<HTMLDivElement>();
    const inputRef = React.createRef<HTMLInputElement>();
    const [filteredItems, setFilteredItems] = useState<FormSelectItem[]>(selectedItem !== null ? items.filter(item => item.value.startsWith(selectedItem.value)) : items);

    const documentClickEventListener = (e: MouseEvent): void => {
        const target = e.target as HTMLElement;
        if (target.closest('.form-select') !== ref.current && active) {
            setActive(false);
        }
    }

    const filterItems = (value: string | null) => {
        if (value === null || value.length === 0) {
            setFilteredItems(items);
        } else {
            setFilteredItems(items.filter(item => item.value.startsWith(value)));
        }
    }

    const innerInputHandler = (e: FormEvent<HTMLInputElement>) => {
        const target = e.target as HTMLInputElement;
        if (target.value.length === 0) {
            inputHandler(null);
        } else {
            const newItem = items.find(item => item.value === target.value);
            inputHandler(newItem === undefined ? {key: null, value: target.value} : newItem);
        }

        filterItems(target.value);
    }

    const innerClickHandler = (newItem: FormSelectItem) => {
        inputHandler(newItem);
        setActive(false);
        filterItems(newItem.value);
    }

    const cleanInput = () => {
        inputHandler(null);
        inputRef.current?.focus();
        setActive(true);
        filterItems(null);
    }

    useEffect(() => {
        document.addEventListener('click', documentClickEventListener);
        return () => {
            document.removeEventListener('click', documentClickEventListener);
        }
    });

    if (active || selectedItem !== null)
        classList.push('form-select_active');

    if (className !== undefined)
        classList.push(className);

    return (
        <div className={classList.join(' ')} ref={ref}>
            <span className={'form-select__title'}>{title}</span>
            <input
                type={'text'}
                className={'form-select__input'}
                value={selectedItem !== null ? selectedItem.value : ''}
                onInput={innerInputHandler}
                ref={inputRef}
                onClick={() => setActive(true)}
            />
            {
                selectedItem !== null
                    ?   <div className={'form-select__erase'}>
                            <img src={eraseIcon} className={'form-select__erase__icon'} alt={'clean'} onClick={cleanInput}/>
                        </div>
                    :   null
            }
            <fieldset className={'form-select__fieldset'}>
                <legend className={'form-select__fieldset__legend'}>{title}</legend>
            </fieldset>
            {
                active && filteredItems.length > 0
                    ?   <div className={'form-select__dropdown'}>
                            {
                                filteredItems.map(
                                    (item, index) => 
                                        <div className={'form-select__dropdown__item'} key={'index_' + index} onClick={() => innerClickHandler(item)}>{item.value}</div>
                                )
                            }
                        </div>
                    :   null
            }
        </div>
    );
}

export default FormSelect;