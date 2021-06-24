import axios from "axios";
import {ANSWER_MODULE} from "@/api/_prefix";

export const insertAnswers = payload => {
    console.log(payload)
    const {
        不太会写这个对象数组怎么传
    } = payload
    return axios.post('${ANSWER_MODULE}/insertAnswers', {
        不太会写这个对象数组怎么传
    }).then(res => {
        return res.data
    })
}
export const getAnswers = payload => {
    // 根据qnid和uid找到该用户的答案
    console.log(payload)
    return axios.get(`${ANSWER_MODULE}/getAnswers/${payload.qnId}?uid=${payload.uid}`).then(res => {
        return res.data
    })
}