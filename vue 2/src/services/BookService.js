import axios from 'axios';

export default {
getBooks(){
    return axios.get('/allBooks')
},
// getBookByTitle(title){
//     return axios.get('/books/')
// },
getBook(id){
    return axios.get(`/books/${id}`)
},
getMinutesRead(readerId){
    return axios.get(`/activities/totalMinutes/${readerId}`)
},
// adding method to add book 
addBook(book){
return axios.post('/addBook', book);
},

}