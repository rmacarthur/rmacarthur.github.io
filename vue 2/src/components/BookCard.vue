<template>
<div>
  <div class="bookCard">
  <h1>{{book.title}}</h1>
  <h3>By: {{book.author}}</h3>
  <router-link v-bind:to="{name:'BookDetails', params: {title: this.book.title}}">Details</router-link> 
  </div>
</div>
</template>

<script>
export default {
  props: {
    book : {}
  }
}
</script>

<style scoped>
.bookCard{
  background-color: rgb(102,102,102);
  height: 200px;
  width : 250px;
  margin: 15px;
  border-radius: 20px;
  padding: 5px;
  text-align: center;
}
</style>