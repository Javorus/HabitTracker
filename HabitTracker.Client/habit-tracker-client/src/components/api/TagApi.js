import { apiClient } from "./ApiClient";

export const getAllTagsForUserIdApi = (userId) => apiClient.get(`/tags/user/${userId}`)
export const createTagApi = (userId, tag) => apiClient.post(`/tags/user/${userId}`, tag)
