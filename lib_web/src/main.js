import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from './router'
import SlideVerify from 'vue-monoplasty-slide-verify';
import "@/assets/global.css"
import VueParticles from 'vue-particles'

Vue.config.productionTip = false
Vue.use(ElementUI,{size:"small"});
Vue.use(SlideVerify);
Vue.use(VueParticles)

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
