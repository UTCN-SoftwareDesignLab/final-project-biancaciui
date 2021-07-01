<template>
  <v-card>
    <v-card-title>
      Movie Store
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          single-line
          hide-details
      ></v-text-field>

      <v-spacer></v-spacer>

      <v-btn @click="CSVReport">Top 10 Sold</v-btn>


    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="movies"
        :search="search"
        @click:row="sellMovie"
        @refresh="refreshList"
    ></v-data-table>

    <SellMovieDialog
        :opened="dialogVisible"
        :movie="selectedMovie"
        @refresh="refreshList"
    ></SellMovieDialog>
  </v-card>
</template>

<script>
import api from "../api";
import SellMovieDialog from "../components/SellMovieDialog";

import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name: "SellMovie",
  components: { SellMovieDialog },
  data() {
    return {
      movies: [],
      search: "",
      headers: [
        {
          text: "Title",
          align: "start",
          sortable: true,
          value: "name",
        },
        { text: "Director", value: "director" },
        { text: "Genre", value: "genres" },
        { text: "Price", value: "price" },
        { text: "Rating", value: "rating" },
        { text: "Description", value: "description" },
      ],
      dialogVisible: false,
      selectedMovie: {},
    };
  },
  methods: {
    // filter(){
    //   this.movies = api.movies.filterByDescription(this.search);
    // },
    sellMovie(movie) {
      this.selectedMovie = movie;
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedMovie = {};
      this.movies = await api.movies.findAll();
    },
    CSVReport(){
      api.movies.bestMoviesCSV();
    },
  },
  created() {
    var socket = new SockJS("http://localhost:8088/api/websocket-endpoint");
    var stompClient = Stomp.over(socket);
    stompClient.connect({},
        // eslint-disable-next-line no-unused-vars
        frame =>{
          stompClient.subscribe("/api/movies/messages", tick =>{
            console.log(tick);
            alert(JSON.parse(tick.body).content);
          })
        }
    )
    this.refreshList();
  },
  mounted() {
    this.refreshList();
  },
};
</script>

<style scoped></style>