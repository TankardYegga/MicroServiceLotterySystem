package org.example.infrastructure.repository;

import org.example.common.Constants;
import org.example.domain.rule.model.aggregates.TreeRuleRich;
import org.example.domain.rule.model.vo.TreeNodeLineVO;
import org.example.domain.rule.model.vo.TreeNodeVO;
import org.example.domain.rule.model.vo.TreeRootVO;
import org.example.domain.rule.repository.IRuleRepository;
import org.example.infrastructure.dao.IRuleTreeDao;
import org.example.infrastructure.dao.IRuleTreeNodeDao;
import org.example.infrastructure.dao.ITreeNodeLineDao;
import org.example.infrastructure.po.RuleTree;
import org.example.infrastructure.po.RuleTreeNode;
import org.example.infrastructure.po.TreeNodeLine;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author： tankardyegga, 微信号:同名
 * @date: 3/6/2023
 * @Copyright： levinforward@163.com
 */
@Repository
public class RuleRepository implements IRuleRepository {

    @Resource
    private IRuleTreeDao ruleTreeDao;
    @Resource
    private IRuleTreeNodeDao ruleTreeNodeDao;
    @Resource
    private ITreeNodeLineDao treeNodeLineDao;

    @Override
    public TreeRuleRich queryTreeRuleRich(Long treeId) {
        // 获得规则树
        RuleTree ruleTree = ruleTreeDao.queryRuleTreeByTreeId(treeId);
        TreeRootVO treeRootVO = new TreeRootVO();
        treeRootVO.setTreeId(ruleTree.getId());
        treeRootVO.setTreeName(ruleTree.getTreeName());
        treeRootVO.setTreeRootNodeId(ruleTree.getTreeRootNodeId());

        // 获得 树结点ID -》 TreeNodeVO
        Map<Long, TreeNodeVO> treeNodeVOMap = new HashMap<>();
        // 整体的思路非常简单，就是先获取树中所有的结点，然后可从每个RuleTreeNode身上来获取结点的ID以及对应的TreeNodeVO对象
        List<RuleTreeNode> ruleTreeNodeList = ruleTreeNodeDao.queryRuleTreeNodeList(treeId);
        for(RuleTreeNode ruleTreeNode: ruleTreeNodeList){
            // ruleTreeNode没有treeNodeV0所需要的连接信息，这里的连接是TreeNodeLineVO类型，而不是TreeNodeLine类型
            // 所以，需要编写代码来从ruleTreeNode获取该结点所有的连接信息
            // 可以通过对ITreeNodeLineDao接口发起请求，查询得到当前ruleTreeNode的TreeNodeLine连接列表
            // 我们只需要设法从每个TreeNodeLine对象获取得到TreeNodeLineVO对象即可
            List<TreeNodeLineVO> treeNodeLineVOList = new ArrayList<>();
            // 注意：需要判断当前ruleTreeNode是否为子叶结点，因为果实结点并不存在任何对应的连接
            if(Constants.NodeType.STEM.equals(ruleTreeNode.getNodeType())){
                TreeNodeLine treeNodeLineReq = new TreeNodeLine();
                treeNodeLineReq.setTreeId(treeId);
                treeNodeLineReq.setNodeIdFrom(ruleTreeNode.getId());
                List<TreeNodeLine>  treeNodeLineList = treeNodeLineDao.queryRuleTreeNodeLineList(treeNodeLineReq);
                for(TreeNodeLine treeNodeLine: treeNodeLineList){
                    TreeNodeLineVO treeNodeLineVO = new TreeNodeLineVO();
                    treeNodeLineVO.setNodeIdFrom(treeNodeLine.getNodeIdFrom());
                    treeNodeLineVO.setNodeIdTo(treeNodeLine.getNodeIdTo());
                    treeNodeLineVO.setLimitType(treeNodeLine.getRuleLimitType());
                    treeNodeLineVO.setLimitValue(treeNodeLine.getRuleLimitValue());
                    treeNodeLineVOList.add(treeNodeLineVO);
                }
            }

            TreeNodeVO treeNodeVO = new TreeNodeVO();
            treeNodeVO.setTreeId(ruleTreeNode.getTreeId());
            treeNodeVO.setTreeNodeId(ruleTreeNode.getId());
            treeNodeVO.setNodeType(ruleTreeNode.getNodeType());
            treeNodeVO.setNodeValue(ruleTreeNode.getNodeValue());
            treeNodeVO.setRuleKey(ruleTreeNode.getRuleKey());
            treeNodeVO.setRuleDesc(ruleTreeNode.getRuleDesc());
            treeNodeVO.setTreeNodeLineVOList(treeNodeLineVOList);
            treeNodeVOMap.put(ruleTreeNode.getId(), treeNodeVO);
        }

        TreeRuleRich treeRuleRich = new TreeRuleRich();
        treeRuleRich.setTreeRoot(treeRootVO);
        treeRuleRich.setTreeNodeMap(treeNodeVOMap);

        return treeRuleRich;
    }
}
