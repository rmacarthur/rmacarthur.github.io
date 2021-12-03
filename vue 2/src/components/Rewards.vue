<template>
<div>

<div id="rewardContainer">
  <RewardCard v-for="reward in rewardList" v-bind:reward="reward" v-bind:key="reward.id"/>
</div>
<h3 id="RewardForm" v-on:click="showForm = !showForm">Add New Reward </h3>
<div v-show=showForm >
            <form v-on:submit="addReward">
            <label for="name">Description: </label>
            <input type='text' id='name' v-model="reward.description">
            <br/>
            <label for="minutesRequired"> Minutes Required to Earn: </label>
            <input type="text" id="minutesRequired" v-model="reward.minutesRequired">
           <br/>
            <label for="maxRecipients"> How many of these are available: </label>
            <input type="number" id="maxRecipients" v-model="reward.maxRecipients">
            <br/>
             <label for="endDate"> When does this offer end? </label>
            <input type="date" id="endDate" v-model="reward.endDate">
            <br/> <br/>
            <button clas="btn btn-submit"> Submit </button>
            </form>
        </div>
</div>
</template>

<script>
import RewardsService from "../services/RewardsService.js"
import RewardCard from "../components/RewardCard.vue"
export default {
components:{RewardCard},
data(){
  return{
    reward : {
      minutesRequired : "",
      endDate : "",
      maxRecipients : "",
      description : "",
      familyId: "",
      
    },
    familyId: this.$route.params.familyId,
    rewardList: [],
    showForm: false,
  }
},
created(){

  //RM had to add created as code wouldnt compile without calling RewardsService

  RewardsService.getRewards(this.familyId).then(
            (response) => {
                this.rewardList = response.data;
            }
  )
},
methods : {
  addReward(){
    const newReward = {
       minutesRequired : this.reward.minutesRequired,
      endDate : this.reward.endDate,
      maxRecipients : this.reward.maxRecipients,
      description : this.reward.description,
      familyId : this.$route.params.familyId
    };
    RewardsService.addRewardFamily(newReward).then(
      (response)=> {
                if(response.status == 200) {
                    this.rewardList.push(newReward);
                }
    
  }
  )
}

}
}
</script>

<style scoped>
#RewardForm:hover{
  text-decoration: underline;
  background-color: rgb(157,210,65);
  
}
#RewardForm{
  margin-left: 80px;
  padding-left: 5px;
  margin-right: 60px;
  border-radius: 10px;
  background-color: rgb(102,102,102);
}
</style>