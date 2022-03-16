import './form-input.css';
import React, {FormEvent, useEffect, useState} from "react";
import {FormDateType} from "./form-date";
import eraseIcon from "../../image/erase.png";
import showPasswordIcon from "../../image/show-password.png";
import hidePasswordIcon from "../../image/hide-password.png";
import errorIcon from "../../image/error.png";

export enum FormInputType {
    TEXT = "text",
    PASSWORD = "password",
    EMAIL = "email"
}

export interface FormInputProps {
    title: string,
    value: string,
    inputHandler: Function,
    locked?: boolean,
    type?: FormInputType | FormDateType,
    alwaysFilled?: boolean,
    className?: string,
    error?: string
}

const FormInput = ({title, value, inputHandler, type = FormInputType.TEXT, alwaysFilled = false, className, error, locked = false}: FormInputProps) => {
    const classList = ['form-input'];
    const [active, setActive] = useState(false);
    const ref = React.createRef<HTMLDivElement>();
    const inputRef = React.createRef<HTMLInputElement>();
    const [visiblePassword, setVisiblePassword] = useState<boolean>(false);
    const [innerType, setInnerType] = useState<FormInputType | FormDateType>(type);

    const documentClickEventListener = (e: MouseEvent): void => {
        const target = e.target as HTMLElement;
        if (target.closest('.form-input') !== ref.current && active) {
            setActive(false);
        }
    }

    const innerInputHandler = (e: FormEvent<HTMLInputElement>) => {
        if (!locked) {
            const target = e.target as HTMLInputElement;
            inputHandler(target.value);
        }
    }

    useEffect(() => {
        document.addEventListener('click', documentClickEventListener);
        return () => {
            document.removeEventListener('click', documentClickEventListener);
        }
    });

    const cleanInput = () => {
        inputHandler('');
        inputRef.current?.focus();
        setActive(true);
    }

    const changePasswordVisibility = () => {
        setInnerType(visiblePassword ? FormInputType.PASSWORD : FormInputType.TEXT);
        setVisiblePassword(!visiblePassword);
    }

    if (active)
        classList.push('form-input_active');

    if (alwaysFilled || value !== '')
        classList.push('form-input_filled');

    if (className !== undefined)
        classList.push(className);

    if (error !== undefined)
        classList.push('form-input_error')

    return (
        <div className={classList.join(' ')} ref={ref}>
            <span className={'form-input__title'}>{title}</span>
            <input
                type={innerType}
                className={'form-input__input'}
                value={value}
                onInput={innerInputHandler}
                ref={inputRef}
                onClick={() => setActive(true)}
            />
            <fieldset className={'form-input__fieldset'}>
                <legend className={'form-input__fieldset__legend'}>{title}</legend>
            </fieldset>
            {
                value !== ''
                    ?   <div className={'form-input__button form-input__erase'}>
                            <img src={eraseIcon} className={'form-input__erase__icon'} alt={'clean'} onClick={cleanInput}/>
                        </div>
                    :   null
            }
            {
                type === FormInputType.PASSWORD
                    ?   <div className={'form-input__button form-input__visible'}>
                            <img
                                src={visiblePassword ? hidePasswordIcon : showPasswordIcon}
                                className={'form-input__visible__icon'}
                                alt={visiblePassword ? 'hide' : 'show'}
                                onClick={changePasswordVisibility}
                            />
                        </div>
                    :   null
            }
            {
                error !== undefined
                    ?   <div className={'form-input__button form-input__error'}>
                            <img src={errorIcon} className={'form-input__error__icon'} alt={'error'}/>
                            {
                                error.length > 0
                                    ?   <div className={'form-input__error__text'}>{error}</div>
                                    :   null
                            }
                        </div>
                    :   null
            }
        </div>
    )
}

export default FormInput;