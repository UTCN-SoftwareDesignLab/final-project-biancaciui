import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";
import "material-design-icons-iconfont/dist/material-design-icons.css";
//
// import "vuetify/dist/vuetify.min.css";
// import "./plugins/vuetify"; //instead of import './plugins/vuetify/lib'

import "./api";
// import stripe from './plugins/stripe';
import store from "./store";

Vue.config.productionTip = false;


new Vue({
  router,
  vuetify,
  // el: "#app",
  store,
  // stripe,
  render: (h) => h(App),
}).$mount("#app");
