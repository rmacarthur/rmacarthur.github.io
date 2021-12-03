<template>
<html>
<body>
  <div id="app">
    <div id="nav">
      <router-link id="nav1" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link id='nav2' v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
  <div>
    <img id="logo" src="https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/6dde3e0f-c1d1-4231-b73d-3f1a0834308e/derkkoo-85539424-c1a5-491d-8e36-2b4332c05a80.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzZkZGUzZTBmLWMxZDEtNDIzMS1iNzNkLTNmMWEwODM0MzA4ZVwvZGVya2tvby04NTUzOTQyNC1jMWE1LTQ5MWQtOGUzNi0yYjQzMzJjMDVhODAuanBnIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.Q8ACCB0CZZRnV4SbBC12XWOs8oz2P3ohrfmHsnLZo-o" alt="Bookworm image"> 
  </div>
    <router-view />
<link href="https://fonts.googleapis.com/css2?family=Philosopher&display=swap" rel="stylesheet">  </div>
</body>
</html>
</template>
<style>

/* html {
background: url(https://images.unsplash.com/photo-1507842217343-583bb7270b66?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1290&q=80) no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  } */
body {
  background-color: rgb(102,190,215);
  width: 100%;
  height: 100%;
  background-size: 100%;
}
#app{
background-color: rgb(102,190,215);
display: flex;
justify-content: center;
align-items: center;
flex-direction: column;
font-family: 'Philosopher', sans-serif;
}
#nav{
  background-color: rgb(102,102,102);
  display: flex;
  flex-direction: row;
  align-content: center;
}
#nav1:hover, #nav2:hover{
  background-color:rgb(157,210,65);;
}
#logo{
  height: 100px;
  width:100px;
  border-radius: 100px;
  margin: 10px;
}

</style>