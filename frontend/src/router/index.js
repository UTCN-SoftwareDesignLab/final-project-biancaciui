import Vue from "vue";
import VueRouter from "vue-router";

import UserList from "../views/UserList.vue";//ADMIN
// import BookList from "../views/BookList.vue";//ADMIN
// import SellBook from "../views/SellBook.vue";//CUSTOMER
import MovieList from "../views/MovieList.vue";//ADMIN
import SellMovie from "../views/SellMovie.vue";//CUSTOMER
import Login from "../views/Login";

import { auth as store } from "../store/auth.module";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  { //ADMIN
    path: "/users",
    name: "Users",
    component: UserList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
        console.log("INDEX ADMIN");
      } else {
        console.log("INDEX EMPLOYEE");
        next({ name: "SellMovie" });
      }
    },
  },
  { //ADMIN
    path: "/movies",
    name: "Movies",
    component: MovieList,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        next();
      } else {
        next({ name: "SellMovie" });
      }
    },
  },
  {
    path: "/customer",
    name: "SellMovie",
    component: SellMovie,
    beforeEnter: (to, from, next) => {
      if (store.state.status.loggedIn) {
        console.log("MOVIE STORE CREATED");
        next();
      } else {
        next({ name: "Home" });
      }
    },
  },

  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
