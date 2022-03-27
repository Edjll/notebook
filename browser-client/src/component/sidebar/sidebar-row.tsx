import './sidebar-row.scss';
import {ReactNode} from "react";

export interface SidebarRowProps {
    children?: ReactNode
}

const SidebarRow = ({children}: SidebarRowProps) => {

    return (
        <div className={'sidebar-row'}>
            {children}
        </div>
    );
}

export default SidebarRow;