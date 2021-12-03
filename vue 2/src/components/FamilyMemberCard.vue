<template>
  <div class="familyCard">
      <h2>Username: {{member.username}}</h2>
      <h3>Family ID: {{member.familyId}} </h3>

      <!-- will need to build out this functionality w/ activity stuff -->
     <router-link v-bind:to="{name:'activitiesForReader', params: {readerId:member.userId}}">Activities</router-link>
  </div>
</template>

<script>
export default {
  props: {
    member: {}, 
    activity : {}
   }

}
</script>

<style>

.familyCard{
  background-color: rgb(102,102,102);
  height: 140px;
  width : 280px;
  margin: 15px;
  border-radius: 20px;
  padding: 5px;
  text-align: center;
}

</style>