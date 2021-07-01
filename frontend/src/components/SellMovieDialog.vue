<script src="https://js.stripe.com/v3"></script>
<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
           Buy Movie
        </v-toolbar>
        <v-form>
          <template slot="lead">

          </template>
          <v-text-field v-model="movie.name" :disabled=true label="Name" />
          <v-text-field v-model="movie.director" :disabled=true label="Director" />
          <v-text-field v-model="movie.rating" :disabled=true label="Rating" />
          <v-text-field v-model="movie.price" :disabled=true label="Price" />
<!--          <v-text-field v-model="movie.description" :disabled=true label="Description" />-->
        </v-form>
        <v-card-actions>
          <v-btn @click="sellButton">
            Buy Movie
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name: "SellMovieDialog",
  props: {
    movie: Object,
    opened: Boolean,
    user_id: Number,
  },
  methods: {
     sellButton(){
      var user = this.$store.getters["auth/getUser"];
      console.log("User id = " + user.id+" and Movie id = "+this.movie.id);
       api.moviestore
        .sell(this.movie.id, user.id)
          .then(() => this.$emit("refresh"))
          .catch((error) => {
          alert(error.response.data);
          });

       var socket = new SockJS("http://localhost:8088/api/websocket-endpoint");
       var stompClient = Stomp.over(socket);
       stompClient.connect(
           {},
           // eslint-disable-next-line no-unused-vars
           frame =>{
             stompClient.send("/messages", JSON.stringify({message: "Order in process: user.username="+user.username+" movie.name="+this.movie.name }))
             //var username = this.$store.getters["auth/getUsername"];
           }
       )
    },
  },
};
</script>


<style scoped></style>
