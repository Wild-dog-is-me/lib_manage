<template>
  <div style="width: 80%">
    <div style="margin-bottom: 30px">编辑分类</div>
    <el-form :inline="true" :model="form" label-width="100px" :rules="rules">
      <el-form-item label="分类名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入分类名称"></el-input>
      </el-form-item>
      <el-form-item label="备注" prop="ramark">
        <el-input v-model="form.remark" placeholder="请输入备注"></el-input>
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
  name: "EditCategory",
  data() {
    return {
      form: {},
      rules: {
        name: [{ required: true, message: "请输入分类名称", trigger: "blur" }],
      },
    };
  },
  created() {
    const id = this.$route.query.id;
    request.get("/category/" + id).then((res) => {
      this.form = res.data;
    });
  },
  methods: {
    save() {
      request.put("/category/update", this.form).then((res) => {
        if (res.code === "200") {
          // this.$router.push("/categoryList")
          this.$notify.success("更新成功");
        } else {
          this.$notify.error(res.msg);
        }
      });
    },
  },
};
</script>
