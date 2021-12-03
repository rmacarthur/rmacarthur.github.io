import axios from 'axios';

 export default {
// addRewardUser(reward){
//    return axios.post('/addRewardUser', reward)
// },

addRewardFamily(reward){
    return axios.post('/addRewardFamily', reward)
},

getRewards(familyId){
    return axios.get(`/allRewards/${familyId}`)
},

getRewardById(id){
return axios.get(`/getReward/${id}`)
},

deleteReward(id){
    if(confirm("Are you sure you want to delete this reward?")){
    return axios.delete(`removeReward/${id}`)}
},

updateReward(reward){
    return axios.put(`/updateReward/${reward.id}`)
}
}