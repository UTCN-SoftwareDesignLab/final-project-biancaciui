import authHeader, { BASE_URL, HTTP } from "../http";

import { saveAs } from 'file-saver';

export default {

    create(movieDTO) {
        return HTTP.post(BASE_URL + "/movies", movieDTO, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },

    findAll() {
        return HTTP.get(BASE_URL + "/movies", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },

    getMovie(id){
        return HTTP.get(BASE_URL + "/movies/" + id, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },

    getGenres(id){
        return HTTP.get(BASE_URL + "/movies/genres/" + id, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },

    edit(id, movieDTO) {
        return HTTP.put(BASE_URL + "/movies/" + id, movieDTO, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },

    changePrice(id, price) {
        return HTTP.patch(BASE_URL + "/movies/" + id, price, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },

    increaseNrReviews(id) {
        return HTTP.patch(BASE_URL + "/movies/" + id +"/incRev", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },

    deleteById(id){
        return HTTP.delete(BASE_URL + "/movies/" + id , {headers: authHeader()}).then(
            () => {
                return true;
            }
        );
    },

    deleteAll(){
    return HTTP.delete(BASE_URL + "/movies", { headers: authHeader() }).then(
            () => {
                return true;
      }
        );
    },

    // filterByDescription(description){
    //     return HTTP.get(BASE_URL + "/movies/" + description,{headers: authHeader()}).then(
    //         (response) => {
    //             return response.data;
    //         }
    //     );
    // },

    findLinkByName(name){
        return HTTP.get(BASE_URL + "/movies/byName/" + name , {headers: authHeader()}).then(
            () => {
                return true;
            }
        );
    },

    findLinkById(id){
        return HTTP.get(BASE_URL + "/movies/byId/" + id , {headers: authHeader()}).then(
            () => {
                return true;
            }
        );
    },

    // //
    // getAllGenres() {
    //     return HTTP.get(BASE_URL + "/movies/genres", { headers: authHeader() }).then(
    //         (response) => {
    //             return response.data;
    //         }
    //     );
    // },



    // REPORTS

    // CSV() {
    //     console.log("CSV");
    //     return HTTP.get(BASE_URL + "/export/CSV", {
    //         headers: authHeader(),
    //     }).then((response) => {
    //         return response.data;
    //     });
    // },

    bestMoviesCSV(){
        console.log("CSV");
        return HTTP.get(BASE_URL + "/movies/export/CSV", { headers: authHeader() , responseType: 'blob'}).then(
            (response) => {
                //console.log(response);
                let blob = new Blob([response.data], {type: "text/csv;charset=utf-8"});
                saveAs(blob, "top10movies.csv");
            }
        );
    }


    // PDF() {
    //     console.log("PDF");
    //     return HTTP.get(BASE_URL + "/export/PDF", {
    //         headers: authHeader(),
    //     }).then((response) => {
    //         return response.data;
    //     });
    // },
    //

};
