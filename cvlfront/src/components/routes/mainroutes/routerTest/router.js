import { createRouter, createWebHistory } from 'vue-router'
import Home from '/src/components/routes/mainroutes/Home.vue';
import AddCustomer from '/src/components/routes//mainroutes/AddCustomer.vue';
import Stats from '/src/components/routes//mainroutes/Stats.vue';
import NotFound from '/src/components/routes//NotFound.vue';
import ModifyCustomer from '/src/components/routes//mainroutes/ModifyCustomer.vue';

export const router = createRouter({
    history: createWebHistory(),
    routes: [
        {path: "/", component:Home},
        {path: "/addCustomer", component:AddCustomer},
        {path: "/stats", component:Stats},
        {path: "/modify/:id", component:ModifyCustomer},
        {path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound },
    ],
})
