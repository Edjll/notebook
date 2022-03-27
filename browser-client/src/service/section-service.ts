import {ApiConstants, ApiService} from "./api-service";

export interface Section {
    id: number,
    name: string
}

export class SectionService {
    private static sections: Section[] = [];

    public static init() {
        return ApiService
            .getInstance()
            .get(ApiConstants.ENDPOINT_SECTIONS)
            .then(response => {
                this.sections = response.data;
            });
    }

    public static getSections(): Section[] {
        return SectionService.sections.slice();
    }
}