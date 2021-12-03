<template>
  <div>
    <!-- {{$store.state.familyId}} -->
      <h1 id="familyTag">Family</h1>
      <div class = "familyMembers">

         <!--  {{familyList}} -->
      
        <FamilyMemberCard v-for="member in familyList" 
                          v-bind:member="member"
                          v-bind:key="member.familyId"></FamilyMemberCard>
      </div>
      <br>
      <div class="family_search">
        <h3 id="addFamilyTag" v-on:click="showForm = !showForm">Add Family Member</h3>
        <div id="familySearchBar" v-show='showForm'>
          <input type="text" id = "search" v-model="input" placeholder="enter username to search">
            <button btn v-on:click="search">Search</button>
             <button btn v-on:click="clear">Clear</button><br><br>
               <div class="user_list">
                 <li id="userlist" v-for="(user, index) in filterList" v-bind:key="index"><div id="userInList">{{user.username}}</div><div id="userButton">
                   <button btn v-on:click="addFamilyMember"> Add Family Member </button> </div>
                </li>
       </div>
              </div>
        </div>
  </div>
</template>

<script>
import FamilyMemberCard from "../components/FamilyMemberCard";
import FamilyService from "../services/FamilyService.js";
import activityService from "../services/ActivityService.js"

export default {
  components: {
    FamilyMemberCard,
  },
  data(){
    return{
      member : {
        name : "",
        password : "",
        isParent : false,
        familyId : this.familyId,
        readerId : this.readerId, 
        activity: ""
      },

      user: {
        username: "",
        familyId: this.familyId ,
        readerId: this.readerId
      },

      activity: {
          username: "",
          title: "",
          date: "",
          minutes: "",
          type: "",
          isCompleted: "",
          notes: "",
          readerId: this.readerId
        },

      familyList : [],
      userList : [],
      filterList : [],
      activitiesList : [],
      input: "",

      showForm: false
    
      
    }
  },
  created() {

    let familyId = this.$route.params.familyId;

    FamilyService.getFamilyList(familyId)
    .then((response) => {
      this.familyList = response.data;
      
    })
    FamilyService.findAllUsers()
    .then(response => {
      this.userList = response.data; 
      
      
    })
  },

  methods: {
    search() {
      return this.filterList = this.userList.filter((user) => {
        return user.username.toLowerCase().includes(this.input.toLowerCase())
      })
    },
    clear() {
      return this.filterList=this.userList;
    },

    getActivitiesForReader(readerId) {
  
    activityService.getActivitiesForReader(readerId).then((response) => {
            this.activitiesList = response.data;
        })
    },

    addFamilyMember(member) {
        this.$store.commit('SET_FAMILY_ID', this.$route.params.familyId);
        FamilyService.addFamilyMember(this.$store.state.familyId, member.userId)
    }
  }, 

  

} 


</script>

<style>

.familyform:hover{
  text-decoration: underline;
}
#familyTag{
  margin-left: 100px;
}
#addFamilyTag{
  margin-left: 70px;
  background-color: rgb(102,102,102);
  width: 175px;
  border-radius: 10px;
}

#userlist {
  height: 25px;
  display: block;
}

#userInList{
  font-weight: 900;
  float: left;
}

#userButton {
  float: right;
}
</style>