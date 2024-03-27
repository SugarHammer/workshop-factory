<template>
<div>
  <router-link to="/testHome/message">message</router-link>
  <router-link to="/testHome/news">news</router-link>
  <h1>---------------------------</h1>
  <router-view></router-view>
</div>
</template>

<script>
  export default {
    name: 'TestHome'
  }
</script>

<style scoped>

</style>
