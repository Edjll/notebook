import {ApiConstants, ApiService} from "./api-service";

export default class ExportService {

    public static exportNotes() {
        ApiService
            .getInstance()
            .get(ApiConstants.ENDPOINT_EXPORT_NOTES, {
                responseType: 'blob'
            })
            .then(response => {
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', 'notes.txt');
                document.body.appendChild(link);
                link.click();
            });
    }

    public static exportTasks() {
        ApiService
            .getInstance()
            .get(ApiConstants.ENDPOINT_EXPORT_TASKS, {
                responseType: 'blob'
            })
            .then(response => {
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', 'tasks.txt');
                document.body.appendChild(link);
                link.click();
            });
    }
}