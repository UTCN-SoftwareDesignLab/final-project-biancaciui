import authHeader, { BASE_URL, HTTP } from "../http";


export default {

    create(orderDTO) {
        return HTTP.post(BASE_URL + "/order", orderDTO, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },

    findAll() {
        return HTTP.get(BASE_URL + "/order", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },

    getMovie(id){
        return HTTP.get(BASE_URL + "/order/" + id, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(id, orderDTO) {
        return HTTP.put(BASE_URL + "/order/" + id, orderDTO, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },

    deleteById(id){
        return HTTP.delete(BASE_URL + "/order/" + id , {headers: authHeader()}).then(
            () => {
                return true;
            }
        );
    },

    deleteAll(){
        return HTTP.delete(BASE_URL + "/order", {headers: authHeader()}).then(
            () => {
                return true;
            }
        );
    },

    countOrdersByMovieID(id){
        return HTTP.get(BASE_URL + "/order/cntOrdMovie/" + id , {headers: authHeader()}).then(
            () => {
                return true;
            }
        );
    },
    countOrdersByUserID(id){
        return HTTP.get(BASE_URL + "/order/cntOrdUser/" + id , {headers: authHeader()}).then(
            () => {
                return true;
            }
        );
    },

}