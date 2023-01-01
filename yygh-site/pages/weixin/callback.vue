<template>
  <!-- header -->
  <div>
  </div>
  <!-- footer -->
</template>
<script>
export default {
  layout: "empty",
  data() {
    return {
    }
  },
  mounted() {
    //新的方法，用路由获取网址中的拼接值
    let token = this.$route.query.token
    let name = this.$route.query.name
    let openid = this.$route.query.openid
    // 调用父vue方法
    window.parent['loginCallback'](name, token, openid)
  }
}
</script>
