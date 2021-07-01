import authHeader, { BASE_URL, HTTP } from "../http";

export default {

    sell(movie_id, user_id){
        return HTTP.post(BASE_URL + "/moviestore/"+ movie_id + "/" + user_id, {}, {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
};
