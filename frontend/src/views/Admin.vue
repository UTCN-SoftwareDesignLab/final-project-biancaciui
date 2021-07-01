<template>
  <div id="admin">
    <h1>Users</h1>
    <div id="nav">
      <router-link to="../MovieList">Movies</router-link> |
      <router-link to="../UserList">Users</router-link> |
    </div>
    <b-container>
      <router-view/>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "Admin.vue"
}
</script>

<style scoped>

</style>