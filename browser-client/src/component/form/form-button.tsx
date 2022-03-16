import './form-button.css';
import {ReactNode} from "react";

export interface FormButtonProps {
    children: ReactNode,
    clickHandler: Function,
    className?: string
}

const FormButton = ({children, clickHandler, className}: FormButtonProps) => {
    let classList = ['form-button'];

    if (className !== undefined) {
        classList.push(className);
    }
    
    return (
        <button className={classList.join(' ')} onClick={() => clickHandler()}>
            {children}
        </button>
    );
}

export default FormButton;