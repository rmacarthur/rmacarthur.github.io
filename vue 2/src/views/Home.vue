<template>
  <div class="home">
    <h1 id="mainTitle">Home</h1>
      <div id="booksButton">
        <router-link v-bind:to="{name:'books'}">Books</router-link>
      </div>
      <br>
      <div id="familyButton">
        <router-link v-bind:to="{name:'familyId', params: {familyId: familyId}}">Family</router-link>
      </div>
      <br>
      <div id="activityButton">
        <router-link v-bind:to="{name:'activitiesForReader', params:{readerId: readerId}}">Activities</router-link>        
      </div>
      <br>
      <div id="rewardsButton">
        <router-link v-bind:to="{name:'rewards', params:{familyId: familyId}}">Rewards</router-link>
      </div>
      <br>
      <div id="minutesRead">
        <h3 id="minsReadText">Total Minutes Read: </h3>
        <p id="minutes"> {{this.minutesRead}} </p>
      </div>
  </div>
</template>

<script>
import bookService from "../services/BookService.js"
import familyService from "../services/FamilyService.js"

export default {
  name: "home",

  data(){
    return{
      minutesRead : -1,
      familyId: -1,
      readerId: this.$store.state.user.id,
    }
  },

  created(){
//    bookService.getReaderId(username).then((response) => {
//      this.readerId = response.data;
//   }),


    bookService.getMinutesRead(this.$store.state.user.id).then(
      (response)=>{
        this.minutesRead = response.data;
      }
    ),
    familyService.getFamilyId(this.$store.state.user.id).then(
      (response) => {
        this.familyId = response.data;
      }
    )
  } 
};
</script>
<style scoped>

#minutes{
  background-color: aliceblue;
  width: 60px;
  height: 40px;
  text-align: center;
  padding-top: 20px;
  margin-left: 65px;
}
#minutesRead{
  margin-left: 0px;
}
#minsReadText{
  margin-left: 20px
}
#booksButton:hover, #familyButton:hover, #rewardsButton:hover, #activityButton:hover{
background-color: rgb(157,210,65);;
}
#booksButton, #familyButton, #rewardsButton, #activityButton{
  background-color: rgb(102,102,102);
  height: 40px;
  border-radius: 20%;
  width: 200px;
  text-align: center;
  margin-left: 0px;
  font-size: 2em;
  
}
#mainTitle{
  text-align: center;
}
</style>