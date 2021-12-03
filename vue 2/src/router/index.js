import Vue from 'vue'
import Router from 'vue-router'
import NotFound from '../views/NotFound.vue'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import BooksView from '../views/BooksView.vue'
import FamilyView from '../views/FamilyView.vue'
import RewardsView from '../views/RewardsView.vue'
import BookDetails from '../components/BookDetails.vue'
import ActivitiesView from '../views/ActivitiesView.vue'
import ActivitiesFamilyView from '../views/ActivitiesFamilyView.vue'
import NewActivity from '../components/NewActivity.vue'
import RewardDetails from '../components/RewardDetails.vue'
Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/not-found",
      name: "NotFound",
      component: NotFound
    },
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/allBooks",
      name: "books",
      component: BooksView,
      meta: {
        requiresAuth:true
      }
    },
    {
      path: "/family/:id",
      name: "family",
      component: FamilyView,
      meta: {
        requiresAuth:true
      }
    },
     {
      path: "/familyList/:familyId",
      name: "familyId",
      component: FamilyView,
      meta: {
        requiresAuth: true
      } 
    },
    {
      path: "/activities/:activityId",
      name: "activities",
      component: ActivitiesView,
      meta: { requiresAuth: true}
    },
    {
      path: "/activities/reader/:readerId",
      name: "activitiesForReader",
      component: ActivitiesView,
      meta: { requiresAuth: true }
    },
    {
      path: "/activities/family/:familyId",
      name: "activitiesForFamily",
      component: ActivitiesFamilyView,
      meta: { requiresAuth: true }
    },
    {
      path: "/activities/addActivity/",
      name: "addActivity",
      component: NewActivity,
      meta: { requiresAuth: true }
    },
    {
      path: "/allRewards/:familyId",
      name: "rewards",
      component: RewardsView,
      meta: {
        requiresAuth:true
      }
    },
      {
        path: '/books/:title',
        name: 'BookDetails',
        component: BookDetails
      },
      {
        path: '/rewards/:id',
        name: 'RewardDetail',
        component: RewardDetails
      },
      {
        path: '/users',
        name: 'userList',
        component: FamilyView,
        meta: {
          requiresAuth:true
        }
      },
      {
        path: '/addFamilyMember/:familyId',
        name: 'addFamily',
        component: FamilyView,
        meta: {
          requiresAuth:true
        }
      }
    
   
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
