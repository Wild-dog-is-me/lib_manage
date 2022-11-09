<template>
  <div style="width: 80%">
    <div style="margin-bottom: 30px">编辑管理员</div>
    <el-form :inline="true" :model="form" label-width="100px" :rules="rules">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item label="联系方式" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入联系方式"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" placeholder="请输入地址"></el-input>
      </el-form-item>
    </el-form>

    <div style="text-align: center; margin-top: 30px">
      <el-button type="primary" @click="save" size="medium">提交</el-button>
    </div>
  </div>
</template>

<script>
import request from "@/api/request";

export default {
  name: 'EditAdmin',
  data() {
    const checkPhone = (rule, value, callback) => {
      if (!/^[1][3,4,5,6,7,8,9][0-9]{9}$/.test(value)) {
        callback(new Error('请输入合法的手机号'));
      }
      callback()
    };
    const validateEmail = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请正确填写邮箱'));
      } else {
        if (value !== '') {
          var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
          if(!reg.test(value)){
            callback(new Error('请输入有效的邮箱'));
          }
        }
        callback();
      }
    };
    return {
      form: {},
      rules: {
        username: [
          { required: true, message: '请输入姓名', trigger: 'blur'}
        ],
        phone: [
          { validator: checkPhone, trigger: 'blur' }
        ],
        email: [
          { validator: validateEmail, trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    const id = this.$route.query.id
    request.get("/admin/" + id).then(res => {
      this.form = res.data
    })
  },
  methods: {
    save() {
      request.put('/admin/update', this.form).then(res => {
        if (res.code === '200') {
          this.$router.push("/adminList")
          this.$notify.success('更新成功')

        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}

</script>

