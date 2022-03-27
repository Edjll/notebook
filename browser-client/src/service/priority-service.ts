import {ApiConstants, ApiService} from "./api-service";

export interface Priority {
    id: number,
    name: string,
    color: string
}

export class PriorityService {
    private static priorities: Priority[] = [];

    public static init() {
        return ApiService
            .getInstance()
            .get(ApiConstants.ENDPOINT_PRIORITIES)
            .then(response => {
                this.priorities = response.data;
            });
    }

    public static getPriorities(): Priority[] {
        return this.priorities.slice();
    }
}