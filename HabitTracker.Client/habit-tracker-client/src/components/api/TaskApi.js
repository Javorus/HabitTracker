import { apiClient } from "./ApiClient";

export const getAllTasksForUserIdApi = (userId) => apiClient.get(`/tasks/user/${userId}`)
export const createTaskApi = (userId, task) => apiClient.post(`/tasks/user/${userId}`, task)