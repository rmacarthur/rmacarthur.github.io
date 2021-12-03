import axios from 'axios';

export default {
    getActivity(activityId) {
        return axios.get(`/activities/${activityId}`)
    },

    getActivitiesForReader(readerId) {
        return axios.get(`/activities/forReader/${readerId}`)
    },

    getActivitiesForFamily(familyId) {
        return axios.get(`/activities/forFamily/${familyId}`)
    },

    addActivity(activity) {
        return axios.post('/addActivity/', activity)
    },

    editActivity(activity) {
        return axios.put(`/editActivity/${activity.activityId}`, activity)
    },

    removeActivity(activityId) {
        return axios.delete(`/removeActivity/${activityId}`)
    }

}