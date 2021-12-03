<template> 
  <div>
      <h1 id="titleBlock">Books</h1>
      <!-- add search bar -->
     
      <input type="text" id = "search" v-model="input" placeholder="enter a title to search">
      <button btn v-on:click="search">Search</button>
      <button btn v-on:click="clear">Clear Search</button>
        <div class="books-container">
            <!-- TODO  create a book card object that shows details on the book -->
            <BookCard class="book-card" v-for="book in filterList" v-bind:book="book" v-bind:key="book.id"> <!--This can be clicked to send to BookDetails.vue for this book--> </BookCard>
        </div>
        <div>
            <h3 id="form-title" v-on:click="showForm = !showForm">Create New Book</h3>
            <form v-show='showForm' v-on:submit.prevent="addBook" class="bookForm">
                <!-- Create form to allow user to enter book manually (for now) -->
                <!-- Must contian Title, Author, ISBN -->
                <!-- May contain description, genre, and notes -->
                <label for="title">Title</label>
            <input type='text' id='title' v-model="book.title">
            <label for="author">Author</label>
            <input type="text" id="author" v-model="book.author">
            <!--  <label for="isbn">ISBN</label>
            <input type="text" id="isbn" maxlength="17" min="13" v-bind="this.book.isbn"> -->

            <button class="btn btn-submit">Add Book</button>
            </form>
        </div>
  </div>
</template>

<script>
import BookCard from "../components/BookCard.vue"
import BookService from '../services/BookService.js';
import bookService from "../services/BookService.js";
export default {
    components : {
        BookCard,

    },
    data(){
        return{
        book: {
            title : "",
            author : "",
            isbn : "",
            description : "",
            genre : "",
            notes : ""

        },
        showForm:false,
       
        bookList : [],
        filterList: [],
        input : ""
        }
    },
    created(){
        bookService.getBooks().then(
            (response) => {
                this.bookList = response.data;
                this.filterList = this.bookList;
            }
        )
    },
    methods : {
        //PLEASE CHECK THIS BEFORE DEPLOYMENT
        search(){
          return this.filterList = this.bookList.filter((book) => {
            //   console.log(this.input.toLowerCase());
              return book.title.toLowerCase().includes(this.input.toLowerCase())})
        },
        clear(){
            return this.filterList=this.bookList;
        },

  
        addBook(){
            const newBook = {
             title : this.book.title,
            author : this.book.author,
        };
            BookService.addBook(newBook)
            .then(response => {
                if(response.status == 200) {
                    this.bookList.push(newBook);
                }
            })
        }
    }
    

}
</script>

<style scoped>
#titleBlock{
    margin-left: 150px;
    
}
#search{
    margin-left: 40px;
}

.book-card{
    margin-left: 50px;
}

#form-title:hover{
  text-decoration: underline;
  background-color: rgb(157,210,65);
  
}
#form-title{
  margin-left: 100px;
  padding-left:25px;
  width:160px;
  border-radius: 10px;
  background-color: rgb(102,102,102);
}
</style>