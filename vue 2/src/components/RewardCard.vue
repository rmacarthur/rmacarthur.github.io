<template>
<div>
  <div class="rewardCard">
  <h1>{{reward.description}}</h1>
  <h3>End Date {{reward.endDate}}</h3>
  <router-link v-bind:to="{name:'RewardDetail', params: {id: this.reward.rewardId}}">Details</router-link> 
  </div>
</div>
</template>

<script>

export default {
  props: {
    reward : {},
   
  },

}

</script>

<style scoped>
.rewardCard{
  background-color: rgb(102,102,102);
  height: 250px;
  width : 250px;
  margin: 15px;
  border-radius: 20px;
  padding: 5px;
  text-align: center;
}
</style>