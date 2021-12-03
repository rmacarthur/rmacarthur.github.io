import axios from 'axios';


export default {

    addFamilyMember(familyId, userId) {
        return axios.put(`/addFamilyMember/${familyId}/${userId}`)
    },

    getFamilyList(familyId){
        return axios.get(`/familyList/${familyId}`)
    },

    getFamilyId(id){
        return axios.get(`/family/${id}`)
    },

    findAllUsers(){
        return axios.get('/users')
    },

    getActivitiesForReader(readerId) {
        return axios.get(`/activities/forReader/${readerId}`)
    },

}