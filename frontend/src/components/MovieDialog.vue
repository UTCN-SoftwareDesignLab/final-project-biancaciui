<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNewMovie ? "Create movie" : "Edit movie" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="movie.name" label="Name" />
          <v-text-field v-model="movie.director" label="Director" />
          <v-text-field v-model="movie.genre" label="Genre" />

          <v-autocomplete
            dense
            multiple
            :items="genres"
            :item-text="getGenre"
            v-model="currentSelectedGenres"
            return-object
          >
          </v-autocomplete>

          <v-text-field v-model="movie.description" label="Description" />
          <v-text-field v-model="movie.rating" label="Rating" />
          <v-text-field v-model="movie.price" label="Price" />
          <v-text-field v-model="movie.link" label="Link" />
          <v-text-field v-model="movie.year" label="Year" />
          <v-text-field
            v-model="movie.number_reviews"
            :disabled="!isNewMovie"
            label="# of Reviews"
          />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNewMovie ? "Create" : "Save" }}
          </v-btn>
          <v-btn v-if="!isNewMovie" @click="deleteMovie">Delete Movie</v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "MovieDialog",
  props: {
    movie: Object,
    opened: Boolean,
    currentMovie: Object,
    currentSelectedGenres: [],
      // genres: Array,
  },
  data() {
    return {
      genres: [
        { id: 1, genre: "ACTION" },
        { id: 2, genre: "COMEDY" },
        { id: 3, genre: "DRAMA" },
        { id: 4, genre: "FANTACY" },
        { id: 5, genre: "HORROR" },
        { id: 6, genre: "THRILLER" },
        { id: 7, genre: "MYSTERY" },
        { id: 8, genre: "ROMANCE" },
        { id: 9, genre: "MUSICAL" },
        { id: 10, genre: "ANIMATION" },
        { id: 11, genre: "SCI_FI" },
        { id: 12, genre: "CRIME" },
      ],
    };
  },
  methods: {
    persist() {
      if (this.isNewMovie) {
        //console.log("Number of reviews "+ this.movie.number_reviews)
        console.log("Number of genres "+ this.currentSelectedGenres.length)
        var str = JSON.stringify(this.currentSelectedGenres, null, 2);
        console.log(str);

        api.movies
          .create({
            name: this.movie.name,
            description: this.movie.description,
            director: this.movie.director,
            rating: this.movie.rating,
            // genres: this.movie.genres,
            genres: this.currentSelectedGenres,
            price: this.movie.price,
            link: this.movie.link,
            year: this.movie.year,
            number_reviews: this.movie.number_reviews,
          })
          .then(() => this.$emit("refresh"));
      } else {
        this.currentMovie = api.movies.getMovie(this.movie.id);
        api.movies
          .edit(this.movie.id, {
            id: this.movie.id,
            name: this.movie.name,
            description: this.movie.description,
            director: this.movie.director,
            rating: this.movie.rating,
            // genres: this.movie.genres,
            genres: this.currentSelectedGenres,
            price: this.movie.price,
            link: this.movie.link,
            year: this.movie.year,
          })
          .then(() => this.$emit("refresh"));
      }
    },
    getGenre(genre) {
      return genre.genre;
    },
    deleteMovie() {
      api.movies.deleteById(this.movie.id).then((response) => {
        if (response) this.$emit("refresh");
      });
    },
  },
  computed: {
    isNewMovie: function () {
      return !this.movie.id;
    },
  },
  mounted() {
    // this.genres = api.movies.getAllGenres();
  },
  created() {
    // this.genres = api.movies.getAllGenres();
  },
};
</script>

<style scoped></style>
