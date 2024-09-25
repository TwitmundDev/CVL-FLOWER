<!--<script setup>
import {ref, computed} from 'vue';
import Home from './mainroutes/Home.vue';
import AddCustomer from './mainroutes/AddCustomer.vue';
import Stats from './mainroutes/Stats.vue';
import NotFound from './NotFound.vue';
import ModifyCustomer from './mainroutes/ModifyCustomer.vue';


const routes = {
  "/": Home,
  "/addCustomer": AddCustomer,
  "/stats": Stats,
  "/modify": ModifyCustomer,
}

//const routes = [
//  {path: "/", component:Home},
//  {path: "/addCustomer", component:AddCustomer},
//  {path: "/stats", component:Stats},
//  {path: "/modify", component:ModifyCustomer},
//]

const currentPath = ref(window.location.hash)

window.addEventListener('hashchange', () => {
  currentPath.value = window.location.hash
})

const currentView = computed(() => {
  return routes[currentPath.value.slice(1) || '/'] || NotFound
})
</script>


<template>
  <component :is="currentView"/>
</template>

<style scoped>

</style>-->
<template></template>