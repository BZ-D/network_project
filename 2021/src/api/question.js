import axios from "axios";
import {QUESTION_MODULE} from "@/api/_prefix";

export const insertQuestions = payload => {
    console.log(payload)
    const {
        不太会写这个对象数组怎么传
    } = payload
    return axios.post('${QUESTION_MODULE}/insertQuestions', {
        不太会写这个对象数组怎么传
    }).then(res => {
        return res.data
    })
}
export const getQuestions = payload => {
    // 根据qnid请求问题
    console.log(payload)
    return axios.get(`${QUESTION_MODULE}/getQuestions/${payload}`).then(res => {
        return res.data
    })
}