import {ApiConstants, ApiService} from "./api-service";

export default class AuthService {

    private static readonly TOKEN_KEY: string = 'token';

    private static authenticated: boolean = false;
    private static token: string;

    public static init(callback: Function) {
        const token = AuthService.parseCookie();
        if (token != null) {
            AuthService.token = token;
            AuthService.authenticated = true;
        }
        callback();
    }

    public static isAuthenticated(): boolean {
        return AuthService.authenticated;
    }

    public static getToken(): string {
        return AuthService.token;
    }

    public static login(username: string, password: string) {
        return ApiService
            .getInstance()
            .post(
                ApiConstants.ENDPOINT_LOGIN,
                {
                    username: username,
                    password: password
                }
            )
            .then(response => {
                AuthService.authenticated = true;
                AuthService.saveCookie(response.data);
                AuthService.token = response.data;
            });
    }

    public static logout() {
        AuthService.clearCookie();
        AuthService.authenticated = false;
        window.location.pathname = '/';
    }

    private static saveCookie(token: string): void {
        document.cookie = `${AuthService.TOKEN_KEY}=${token}; path=/; max-age=10000000000`;
    }

    private static clearCookie() {
        document.cookie = `${AuthService.TOKEN_KEY}=; path=/; max-age=-1`;
    }

    private static parseCookie(): string | null {
        const cookies = document.cookie.split('; ');
        const dirtyToken: string | undefined = cookies.find(row => row.startsWith(`${AuthService.TOKEN_KEY}=`));
        const token: string | null = dirtyToken !== undefined ? dirtyToken.split('=')[1] : null;
        return token;
    }
}