<template>
  <div class="container">
        <div class='flex-parent jc-center'>
          <router-link v-bind:to="{name: 'addActivity'}">
            <h3 type='submit' id='addActivityBtn'>Add An Activity</h3>
          </router-link>
        </div>


        <div class='flex-parent jc-center'>
          <h1>Activity Details</h1>
        </div>
      
      <table>
        <thead>
          <tr>
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
            <td> {{ activity.bookTitle }} </td>
            <td> {{ activity.timeInMinutes }} </td>
            <td> {{ activity.readingType }} </td>
            <td> {{ activity.notes }} </td>
            <td> {{ activity.dateEntered }} </td>
            <td> {{ activity.isCompleted }} </td>
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
        let readerId = this.$route.params.readerId;

        activityService.getActivitiesForReader(readerId).then((response) => {
            this.activitiesList = response.data;
        })
        .catch(error => {
            if(error.response.status == 404) {
                this.$router.push("/not-found");
            }
        })
    },
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

#addActivityBtn {
  width:140px;
  border-radius: 10px;
  background-color: rgb(102,102,102);
}


</style>