export const judgeLogin = () => {
  return !!window.localStorage.getItem("userId")
}