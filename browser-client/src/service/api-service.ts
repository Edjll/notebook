import axios from "axios";
import AuthService from "./auth-service";

export class ApiConstants {
    public static readonly BACKEND_URL: string = "http://localhost:8080/api";

    public static readonly USERS: string = "/users";
    public static readonly TASKS: string = "/tasks";
    public static readonly NOTES: string = "/notes";
    public static readonly PRIORITIES: string = "/priorities";
    public static readonly SECTIONS: string = "/sections";
    public static readonly EXPORT: string = "/export";

    public static readonly ENDPOINT_LOGIN: string = ApiConstants.BACKEND_URL + ApiConstants.USERS + "/login";

    public static readonly ENDPOINT_TASKS: string = ApiConstants.BACKEND_URL + ApiConstants.TASKS;
    public static readonly ENDPOINT_TASK: string = ApiConstants.BACKEND_URL + ApiConstants.TASKS + "/%id";
    public static readonly ENDPOINT_TASKS_ACTUAL: string = ApiConstants.BACKEND_URL + ApiConstants.TASKS + "/actual";
    public static readonly ENDPOINT_TASKS_COMPLETED: string = ApiConstants.BACKEND_URL + ApiConstants.TASKS + "/completed";
    public static readonly ENDPOINT_TASKS_EXPIRED: string = ApiConstants.BACKEND_URL + ApiConstants.TASKS + "/expired";
    public static readonly ENDPOINT_TASKS_UPDATE_STATUS: string = ApiConstants.BACKEND_URL + ApiConstants.TASKS + "/%id/status";
    public static readonly ENDPOINT_TASKS_DELETE: string = ApiConstants.BACKEND_URL + ApiConstants.TASKS + "/%id";

    public static readonly ENDPOINT_NOTES: string = ApiConstants.BACKEND_URL + ApiConstants.NOTES;
    public static readonly ENDPOINT_NOTE: string = ApiConstants.BACKEND_URL + ApiConstants.NOTES + "/%id";

    public static readonly ENDPOINT_PRIORITIES: string = ApiConstants.BACKEND_URL + ApiConstants.PRIORITIES;

    public static readonly ENDPOINT_SECTIONS: string = ApiConstants.BACKEND_URL + ApiConstants.SECTIONS;

    public static readonly ENDPOINT_EXPORT_NOTES: string = ApiConstants.BACKEND_URL + ApiConstants.EXPORT + ApiConstants.NOTES;
    public static readonly ENDPOINT_EXPORT_TASKS: string = ApiConstants.BACKEND_URL + ApiConstants.EXPORT + ApiConstants.TASKS;
}

export class ApiService {

    private static axi = axios.create();

    public static init() {
        ApiService.axi.interceptors.request.use((config) => {
            if (AuthService.isAuthenticated()) {
                config.headers = {
                    Authorization: AuthService.getToken()
                };
            }
            return config;
        });
    }

    public static getInstance() {
        return ApiService.axi;
    }
}