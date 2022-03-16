import React from "react";
import FormInput from "./form-input";
import './form-date.css';

export enum FormDateType {
    DATE = "date",
    DATETIME_LOCAL = "datetime-local",
    MONTH = "month",
    TIME = "time",
    WEEK = "week"
}

export interface FormDateProps {
    title: string,
    value: string,
    inputHandler: Function,
    type?: FormDateType
}

const FormDate = ({title, value, inputHandler, type = FormDateType.DATE}: FormDateProps) => {
    return (
        <FormInput
            title={title}
            value={value}
            inputHandler={inputHandler}
            type={type}
            alwaysFilled={true}
            className={'form-date'}
        />
    )
}

export default FormDate;