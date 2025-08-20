import axios from 'axios';

const BASE_URL = 'http://localhost:8081/api'; // Change if your backend runs on a different port

// --- TASKS ---
export const getTasksByDate = (when) => axios.get(`${BASE_URL}/tasks/by-date?when=${when}`);
export const createTask = (task) => axios.post(`${BASE_URL}/tasks`, task);
export const updateTask = (id, task) => axios.put(`${BASE_URL}/tasks/${id}`, task);
export const deleteTask = (id) => axios.delete(`${BASE_URL}/tasks/${id}`);
