<template>
  <div id ="details">
    <div id="rewardsButton">
        <router-link v-bind:to="{name:'rewards', params:{familyId: familyId}}">Rewards</router-link>
      </div>
  <h1 calss="details">{{this.reward.description}}</h1>
  <h3 calss="details">To earn this you need to read {{this.reward.minutesRequired}} minutes</h3>
  <p calss="details">There are {{this.reward.maxRecipients}} of these available</p>
  <p calss="details">You need to redeem this reward by {{this.reward.endDate}}</p>
   <!-- <button btn v-on:click="updateReward">Edit Reward</button> -->
      <!-- <button btn v-on:click="claimReward">Claim Reward</button> -->
       <button btn v-on:click="deleteReward">Delete Reward</button>
      
  </div>
</template>

<script>
import RewardsService from "../services/RewardsService.js"
import familyService from "../services/FamilyService.js"
export default {

data(){
  return{
    reward : {
      rewardId : "",
      familyId : "",
      minutesRequired : "",
      startDate : "",
      endDate : "",
      maxRecipients : "",
      description : ""
    },
    familyId : -1
}
},

created(){
    this.reward.id = this.$route.params.id;
    RewardsService.getRewardById(this.reward.id).then(
        (response) => {
            this.reward = response.data
        }
    );
         familyService.getFamilyId(this.$store.state.user.id).then(
      (response) => {
       this.familyId = response.data;});
},
methods : {
    deleteReward(){
      RewardsService.deleteReward(this.reward.rewardId);
      this.$router.push(`/allRewards/${this.familyId}`)

    },

    updateReward(){
      RewardsService.updateReward(this.reward)
    },

    // claimReward(){
    //   this.reward.claimReward = true;
    //   RewardsService.updateReward(this.reward)
    // },

}
}
</script>

<style scoped>
#details{
background-color:rgb(102,102,102);
 border-radius: 15px;
    width:350px;
    height:250px;
    padding: 10px;
}
</style>