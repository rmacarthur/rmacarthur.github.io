<template>
<div id="bookDetailDiv">
      <div id="booksButton">
        <router-link v-bind:to="{name:'books'}">Books</router-link>
      </div>
  <h1>{{book.title}}</h1>
  <h3>By: {{book.author}}</h3>
  <p>{{book.description}}</p>


</div>
</template>

<script>
import bookService from "../services/BookService.js";
export default {
data(){
    return{
        book: {
            title: "",
            author: "",
            description: "",
            notes: "",
            
        },
        minutesRead: -1
    }
},
created(){
    this.book.title = this.$route.params.title;
    bookService.getBook(this.book.title).then(
        (response) => {
            this.book = response.data
        }
    );
    bookService.getMinutesRead().then(
        (response) => this.minutesRead = response.data
    )
},
methods : {
    //TODO get this working right, needs back end work done to allow
    submitForm(){
        const updateBook = {
            notes : this.book.notes,
            minutesRead : this.book.minutesRead
        }; bookService.updateReadingActivity(updateBook).then(
            (response)=>{
                this.book = response.data
            }
        )

    }
}
}
</script>

<style scoped>
#bookDetailDiv{
    background-color:rgb(102,102,102);
    border-radius: 15px;
    width:350px;
    height:250px;
    padding: 10px;
}
</style>