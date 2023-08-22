import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

class ApiService {
    async initiateSession(name) {
        return await axios.post(API_BASE_URL + "/initiate", "name=" + name);
    }
    async submitResturant(viewKey, postData) {
        return await axios.post(API_BASE_URL + "/submit/" + viewKey, postData);
    }
    async view(viewKey) {
        return await axios.get(API_BASE_URL + "/view/" + viewKey);
    }
    async endSession(adminKey) {
        return await axios.get(API_BASE_URL + "/end/" + adminKey);
    }


}

export default new ApiService();