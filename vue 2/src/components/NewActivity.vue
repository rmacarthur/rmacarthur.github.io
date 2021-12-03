<template>
  <div>
    <div>
        <h3 id="form-title">Create New Activity</h3>
        <form v-on:submit.prevent="addActivity" class="activityForm">
          <label for="bookTitle">Title</label>
            <input type='text' id='bookTitle' v-model='activity.bookTitle'>
          <label for="bookTitle">Author</label>
            <input type='text' id='author' v-model='activity.author'>
          <label for='timeInMinutes'>Minutes</label>
            <input type="number" id='timeInMinutes' v-model.number='activity.timeInMinutes'>
          <label for='readingType'>Format</label>
            <select id='readingType' v-model='activity.readingType'>
              <option>Book</option>
              <option>E-Book</option>
              <option>Audiobook</option>
              <option>Read-Aloud (Reader)</option>
              <option>Read-Aloud (Listener)</option>
              <option>Other</option>
            </select>
          <label for='notes'>Notes</label>
            <input type='text' id='notes' v-model='activity.notes'>
          <label for='isCompleted'>Finished Book?</label>
            <input type='checkbox' id='isCompleted' v-model='activity.isCompleted'>
          <button class="btn btn-submit">Add Activity</button>
        </form>
    </div>
  </div>
</template>

<script>
import activityService from "../services/ActivityService.js"

export default {
    data() {
      return {
        activity: {
          readerId: "",
          bookTitle: "",
          author: "",
          minutes: "",
          type: "",
          isCompleted: "",
          notes: ""
        },

        activitiesList: []
      }
    },
    methods : {
      addActivity() {
        const newActivity = {
          readerId: this.$store.state.user.id,
          bookTitle: this.activity.title,
          author: this.activity.author,
          minutes: this.activity.minutes,
          type: this.activity.type,
          isCompleted: this.activity.isCompleted,
          notes: this.activity.notes
        }
          activityService.addActivity(newActivity).then(response => {
            if(response.status == 200) {
              this.activitiesList.push(newActivity);
            }
          })
      }
    }
}
</script>

<style>

</style>