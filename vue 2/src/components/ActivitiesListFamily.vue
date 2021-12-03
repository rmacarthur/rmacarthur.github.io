<template>
  <div class="container">
    <div class='flex-parent jc-center'>
      <h1>Family Activity Details</h1>
    </div>
    <table id="tblFamilyActivities">
      <thead>
        <tr>
          <th @click="sort('username')" >User Name</th>
          <th @click="sort('title')" >Book Title</th>
          <th @click="sort('minutes')" >Reading Minutes</th>
          <th @click="sort('type')" >Reading Type</th>
          <th>Notes</th>
          <th @sort="sort('date')" >Entry Date</th>
          <th @sort="sort('isCompleted')" >Book Finished?</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="activity in activitiesList" v-bind:key="activity.activityId" >
          <td> {{ activity.readerName }} </td>
          <td> {{ activity.bookTitle }} </td>
          <td> {{ activity.timeInMinutes }} </td>
          <td> {{ activity.readingType }} </td>
          <td> {{ activity.notes }} </td>
          <td> {{ activity.dateEntered }} </td>
          <td> {{ activity.completed }} </td>
        </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
import activityService from "../services/ActivityService.js"

export default {
    data() {
      return {
        activity: {
          username: "",
          title: "",
          date: "",
          minutes: "",
          type: "",
          isCompleted: "",
          notes: ""
        },

        activitiesList: []
      }
    },

    created() {
      let familyId = this.$route.params.familyId;

      activityService.getActivitiesForFamily(familyId).then((response) => {
        this.activitiesList = response.data;
        
      })
      .catch(error => {
        if(error.response.status == 404) {
          this.$router.push("/not-found");
        }
      })
    }
}
</script>

<style scoped>

.flex-parent {
  display: flex;
}

.jc-center {
  justify-content: center;
}

table {
  border: 2px solid black;
  width: 90%;
  align-content: center;
  margin-left: auto;
  margin-right: auto;
}

thead {
    background: rgb(102,102,102);
    border: 2px solid black;
}

th, td {
  padding: 15px;
  text-align: center;

}

td {
    background-color: white;
}

tr:hover td {background-color: rgb(157, 210, 65);}

</style>