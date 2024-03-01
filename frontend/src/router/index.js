import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)


const routes = [
  {
    path:'/',
    name:'index',
    redirect: '/user/home'
  },
  {
    path:'/manager',
    name:'manager',
    component:()=>import('@/view/Manager.vue'),
    // redirect:'/login',
    children:[
      {path:'home',name:'Home',meta: { name: '后台首页' }, component: () => import('../view/manager/Home.vue')},
      { path: 'goods', name: 'Goods', meta: { name: '商品信息' }, component: () => import('../view/manager/Goods.vue') },
      { path: 'shopPerson', name: 'ShopPerson', meta: { name: '商家信息' }, component: () => import('../view/manager/ShopPerson.vue') },
      { path: 'password', name: 'Password', meta: { name: '修改密码' }, component: () => import('../view/manager/Password.vue') },
      { path: 'orders', name: 'Orders', meta: { name: '订单管理' }, component: () => import('../view/manager/Orders.vue') },
      { path: 'comment', name: 'Comment', meta: { name: '评论管理' }, component: () => import('../view/manager/Comment.vue') },

    ]
  },
  {
    path:'/user',
    name:'user',
    component:()=>import('@/view/Index.vue'),
    // redirect:'/login',
    children:[
      { path: 'home', name: 'Home', meta: { name: '系统首页' }, component: () => import('../view/user/Home') },
      { path: 'search', name: 'Search', meta: { name: '搜索页面' }, component: () => import('../view/user/Search') },
      { path: 'person', name: 'Person', meta: { name: '个人信息页面' }, component: () => import('../view/user/Person') },
      { path: 'detail', name: 'Detail', meta: { name: '商品详情页面' }, component: () => import('../view/user/Detail.vue') },

    ]
  },
  {
    path:'/login',
    name:'login',
    component:()=>import('@/view/Login.vue'),
    children:[]
  },{
    path:'/register',
    name:'register',
    component:()=>import('@/view/Register.vue'),
  },{
    path:'/forget',
    name:'forget',
    component:()=>import('@/view/Forget.vue'),
  },{
    path:'*',
    name:'not',
    component:()=>import('@/view/404.vue'),
  }
  ]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
export default router

