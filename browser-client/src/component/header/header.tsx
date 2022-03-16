import './header.css';
import {useEffect, useState} from "react";

const Header = () => {
    const [date, setDate] = useState<Date>(new Date());
    let interval;

    useEffect(() => {
        setTimeout(() => {
            setDate(new Date());
            interval = setInterval(() => setDate(new Date()), 1000);
        }, 1000 - date.getMilliseconds());
    }, []);

    return (
        <header className={'header'}>
            <div className={'header__inner'}>
                <div className={'header__logo'}>
                    <span className={'header__logo__text'}>Ежедневник</span>
                </div>
                <div className={'header__date'}>
                    <span className={'header__date__date'}>{date.toLocaleDateString()}</span>
                    <span className={'header__date__time'}>{date.toLocaleTimeString()}</span>
                </div>
            </div>
        </header>
    )
}

export default Header;