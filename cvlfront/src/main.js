//import './assets/main.css'
import './assets/css/main.css'
import { createApp } from 'vue'
import App from './App.vue'
import Header from './components/_base/Header.vue'
import Footer from './components/_base/Footer.vue'
import { router } from './components/routes/mainroutes/routerTest/router'
export const ApiUrl = 'http://localhost:8080/api/v1'

createApp(App).use(router).mount('#app')
createApp(Header).mount('#header')
createApp(Footer).mount('#footer')
