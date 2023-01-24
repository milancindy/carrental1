// 注册账号
export const register = (params) => {
    return http.fetchPost('/member/register', params)
  }