<script src="../router/index.js"></script>
<template>
  <v-card>
    <v-card-title>
      Movies
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search Movie"
        single-line
        hide-details
      ></v-text-field>
      <v-spacer></v-spacer>
      <v-btn @click="addMovie">Add Movie</v-btn>
      <v-btn @click="CSVReport">Top 10 Sold</v-btn>
<!--      <v-btn @click="PDFReport">PDF Report</v-btn>-->
      <v-btn @click="go2usersView">► Users</v-btn>
    </v-card-title>

    <v-data-table
      :headers="headers"
      :items="movies"
      :search="search"
      @click:row="editMovie"
    ></v-data-table>

      <MovieDialog
          :opened="movieDialogVisible"
          :movie="selectedMovie"
          @refresh="refreshList"
      ></MovieDialog>

  </v-card>
</template>

<script>
import api from "../api";
import MovieDialog from "../components/MovieDialog";
import SellMovieDialog from "../components/SellMovieDialog";
import router from "@/router";

export default {
  name: "MovieList",
  components: { MovieDialog, SellMovieDialog },
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
        // { text: "Genres", value:  "genres.genre" },
        { text: "Genres", value: "genres" },
        { text: "Description", value: "description"},
        { text: "Rating", value: "rating" },
        { text: "# Reviews", value: "number_reviews" },
        { text: "Price", value: "price" },
        { text: "Link", value: "link" },
      ],
      movieDialogVisible: false,
      selectedMovie: {},
    };
  },
  methods: {
    // addGenres: function () {
    //   this.push({ value: '' });
    // },

    editMovie(movie) {
      this.selectedMovie = movie;
      this.movieDialogVisible = true;
    },
    addMovie() {
      this.movieDialogVisible = true;
    },
    CSVReport(){
      api.movies.bestMoviesCSV();
    },
    PDFReport(){
    //  api.movies.PDF();
    },
    async refreshList() {
      this.movieDialogVisible = false;
      this.selectedMovie = {};
      this.movies = await api.movies.findAll();
    },
    go2usersView(){
      router.push("/users");
    },
  },
  computed: {
    isAdmin: function () {
      return this.$store.getters.isAdmin;
    },
  },
  created() {
    this.refreshList();
  },
  mounted() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
